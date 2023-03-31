import React, { useState, useEffect, useRef } from 'react';
import '../../App2.css';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const Main1 = () => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');
  const clientRef = useRef(null);

  useEffect(() => {
    clientRef.current = new Client({
      webSocketFactory: () => new SockJS('http://localhost:9090/chat-websocket'),
    });

    clientRef.current.onConnect = (frame) => {
      clientRef.current.subscribe('/topic/messages', (message) => {
        setMessages((prevMessages) => [...prevMessages, message.body]);
      });
    };

    clientRef.current.activate();
    return () => {
      clientRef.current.deactivate();
    };
  }, []);

  const sendMessage = () => {
    if (input && clientRef.current.connected) {
      clientRef.current.publish({ destination: '/app/chat', body: input });
      setInput('');
    }
  };

  return (
    <div className="App">
      <h1>React & Spring Boot WebSocket Chat</h1>
      <div className="chat-window">
        {messages.map((message, index) => (
          <p key={index}>{message}</p>
        ))}
      </div>
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyUp={(e) => {
          if (e.key === 'Enter') {
            sendMessage();
          }
        }}
      />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
}

export default Main1;
