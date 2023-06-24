import React, { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import PropTypes from 'prop-types';
import { Client } from '@stomp/stompjs';
import { StyledErrorMsg } from 'styled-components/StyledForm';
import { useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faCalendar,
  faClock,
  faEnvelope,
  faPaperclip,
} from '@fortawesome/free-solid-svg-icons';

const ChattingRoom = ({ roomId }) => {
  const location = useLocation();
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const [userCount, setUserCount] = useState(0);
  const clientRef = useRef(null);

  // 2. location.state 에서 파라미터 취득
  const { chattingRoomId } = location.state;
  const { userId } = location.state;

  console.log(chattingRoomId);
  console.log(userId);

  /**
   * TODO:
   * Make test chatting app
   * 1. Click chatting room nav
   * 2. Get room id
   * 3. Make sure who is in this room, how many of user is using,
   * and i'm the reader or writer.
   * 4. chat each other.
   *
   */

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
    clientRef.current = new Client({
      webSocketFactory: () =>
        new SockJS('http://localhost:9090/chat-websocket/'),
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

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = (e) => {
    e.preventDefault();
    const files = Array.from(e.dataTransfer.files);
    if (files.length) {
      const confirmSend = window.confirm(
        'Do you really want to send this file?'
      );
      if (confirmSend) {
        // send the message with file
        sendMessage(files[0].name, true);
      }
    }
  };

  const chatWindowRef = useRef(null);

  useEffect(() => {
    const setChatWindowHeight = () => {
      if (chatWindowRef.current) {
        chatWindowRef.current.style.maxHeight = `${window.innerHeight * 0.5}px`;
      }
    };

    setChatWindowHeight();
    window.addEventListener('resize', setChatWindowHeight);

    return () => {
      window.removeEventListener('resize', setChatWindowHeight);
    };
  }, []);

  return (
    <div className="chat-main">
      <h1>React & Spring Boot WebSocket Chat - Room {roomId}</h1>
      <p>Users in the room: {userCount}</p>
      <div className="chat-window" ref={chatWindowRef}>
        {messages.map((message, index) => (
          <p key={`message ${index + 1}`}>{message}</p>
        ))}
      </div>
      {errorMsg ? <StyledErrorMsg>{errorMsg}</StyledErrorMsg> : ''}
      <div className="chat-textarea-box">
        <textarea
          value={input}
          className="chat-textarea"
          onDragOver={handleDragOver}
          onDrop={handleDrop}
          onChange={(e) => setInput(e.target.value)}
          onKeyUp={(e) => {
            if (e.key === 'Enter' && !e.shiftKey) {
              e.preventDefault();
              sendMessage(input);
            }
          }}
        />
      </div>
      <div className="chat-options">
        <div className="chat-options-buttons">
          <div className="ml-10 mr-10">
            <FontAwesomeIcon icon={faEnvelope} />
          </div>
          <div className="ml-10 mr-10">
            <FontAwesomeIcon icon={faCalendar} />
          </div>
          <div className="ml-10 mr-10">
            <FontAwesomeIcon icon={faClock} />
          </div>
          <div className="ml-10 mr-10">
            <FontAwesomeIcon icon={faPaperclip} />
          </div>
        </div>
        <div>
          <button type="button" onClick={() => sendMessage(input)}>
            Send
          </button>
        </div>
      </div>
    </div>
  );
};

ChattingRoom.propTypes = {
  roomId: PropTypes.string.isRequired,
};

export default ChattingRoom;
