import React, { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import PropTypes from 'prop-types';
import { Client } from '@stomp/stompjs';
import { StyledErrorMsg } from 'styled-components/StyledForm';

const ChattingRoom = ({ roomId }) => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const [userCount, setUserCount] = useState(0);
  const clientRef = useRef(null);

  const handleError = (errorMessage) => {
    setErrorMsg(errorMessage.body);
  };

  const sendMessage = () => {
    if (input && clientRef.current.connected) {
      clientRef.current.publish({
        destination: `/app/chat/${roomId}`,
        body: input,
      });
      setInput('');
    }
  };

  useEffect(() => {
    console.log('testset!!!');
    clientRef.current = new Client({
      webSocketFactory: () =>
        new SockJS('http://localhost:9090/chat-websocket'),
    });

    clientRef.current.onConnect = () => {
      clientRef.current.subscribe(`/topic/messages/${roomId}`, (message) => {
        setMessages((prevMessages) => [...prevMessages, message.body]);
      });

      clientRef.current.subscribe(
        `/topic/chat/${roomId}/userCount`,
        (message) => {
          setUserCount(parseInt(message.body, 10));
        }
      );

      clientRef.current.subscribe('/queue/errors', handleError);
      console.log('handleError :: ', handleError);

      // Join the room
      clientRef.current.publish({ destination: `/app/chat/${roomId}/join` });
    };

    clientRef.current.onWebSocketClose = (event) => {
      console.log('event :: ', event);
      if (event.wasClean === false) {
        setErrorMsg(event.reason);
      }
    };

    clientRef.current.activate();
    return () => {
      // Leave the room when unmounting
      if (clientRef.current.connected) {
        clientRef.current.publish({ destination: `/app/chat/${roomId}/leave` });
      }
      clientRef.current.deactivate();
    };
  }, [roomId]);

  return (
    <div className="chat-main">
      <h1>React & Spring Boot WebSocket Chat - Room {roomId}</h1>
      <p>Users in the room: {userCount}</p>
      <div className="chat-window">
        {messages.map((message, index) => (
          <p key={`message ${index + 1}`}>{message}</p>
        ))}
      </div>
      {errorMsg ? <StyledErrorMsg>{errorMsg}</StyledErrorMsg> : ''}
      <input
        type="text"
        value={input}
        className="chat-input"
        onChange={(e) => setInput(e.target.value)}
        onKeyUp={(e) => {
          if (e.key === 'Enter') {
            sendMessage();
          }
        }}
      />
      <button type="button" className="chat-send-button " onClick={sendMessage}>
        Send
      </button>
    </div>
  );
};

ChattingRoom.propTypes = {
  roomId: PropTypes.string.isRequired,
};

export default ChattingRoom;
