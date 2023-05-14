import { createRandomChatMessage } from 'component/utility/FakeUser';
import React, { useState, useEffect } from 'react';
import '../../css/style.css';

const ChattingRoom = () => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');

  // These userIds are now constant for each render of the component
  const currentUserId = 'test1';
  const anotherUserId = 'test2';

  useEffect(() => {
    // create some random chat messages for testing
    const randomMessages = Array.from({ length: 30 }).map(() =>
      Math.random() > 0.5
        ? createRandomChatMessage(currentUserId, anotherUserId)
        : createRandomChatMessage(anotherUserId, currentUserId)
    );
    setMessages(randomMessages);
  }, []); // add these dependencies to the effect

  const sendMessage = () => {
    const newMessage = createRandomChatMessage(currentUserId, anotherUserId);
    newMessage.content = input;
    setMessages([...messages, newMessage]);
  };

  return (
    <div className="chat-main">
      <div className="chat-window">
        {messages.map((message) => (
          <div className="message-wrapper" key={`${message.id}`}>
            <div
              className={
                message.senderId === currentUserId
                  ? 'bubble-left-container'
                  : 'bubble-right-container'
              }
            >
              {message.senderId === currentUserId && (
                <img src={`${message.avatar}`} alt="face" />
              )}
              <p
                className={
                  message.senderId === currentUserId
                    ? 'bubble-left'
                    : 'bubble-right'
                }
                dangerouslySetInnerHTML={{
                  __html: message.content.replace(/\n/g, '<br />'),
                }}
              />
            </div>
          </div>
        ))}
      </div>
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
      <button className="chat-send-button" type="button" onClick={sendMessage}>
        Send
      </button>
    </div>
  );
};

export default ChattingRoom;
