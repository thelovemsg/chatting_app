import React, { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import { StyledErrorMsg } from 'styled-components/StyledForm';

const ChattingRoom = () => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const clientRef = useRef(null);

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

  useEffect(() => {
    clientRef.current = new Client({
      webSocketFactory: () =>
        new SockJS('http://localhost:9090/chat-websocket'),
    });

    clientRef.current.onConnect = () => {
      clientRef.current.subscribe('/topic/messages', (message) => {
        setMessages((prevMessages) => [...prevMessages, message.body]);
      });
      // Subscribe to the /queue/errors destination
      clientRef.current.subscribe('/queue/errors', handleError);
    };

    clientRef.current.onWebSocketClose = (event) => {
      if (event.wasClean === false) {
        setErrorMsg(event.reason);
        // You can display an error message to the user or handle the error as needed
      }
    };

    clientRef.current.activate();
    return () => {
      clientRef.current.deactivate();
    };
  }, []);

  const handleError = (errorMessage) => {
    setErrorMsg(errorMessage.body);
    // Display the error message to the user or handle it as needed
  };

  const sendMessage = () => {
    if (input && clientRef.current.connected) {
      clientRef.current.publish({ destination: '/app/chat', body: input });
      setInput('');
    }
  };

  return (
    <div className="chat-main">
      <h1>React & Spring Boot WebSocket Chat</h1>
      <div className="chat-window">
        {messages.map((message, index) => (
          <p key={index}>{message}</p>
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
      <button className="chat-send-button " onClick={sendMessage}>
        Send
      </button>
    </div>
  );
};

export default ChattingRoom;
