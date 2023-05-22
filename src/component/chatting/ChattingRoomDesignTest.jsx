import { createRandomChatMessage } from 'component/utility/FakeUser';
import React, { useState, useEffect, useRef } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFolderOpen } from '@fortawesome/free-solid-svg-icons';
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

  const sendMessage = (content, hasFile = false) => {
    const newMessage = createRandomChatMessage(currentUserId, anotherUserId);
    const check = !!content;
    if (check) {
      newMessage.content = content;
      newMessage.hasFile = hasFile;
      setMessages([...messages, newMessage]);
      if (content === input) {
        setInput('');
      }
    }
  };

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

  const getBubbleClassName = (message) => {
    if (message.senderId === currentUserId) {
      return message.hasFile ? 'bubble-left-with-file' : 'bubble-left';
    }

    return 'bubble-right';
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
      <div className="chat-window" ref={chatWindowRef}>
        {messages.map((message, index) => (
          <div key={`${message.userId + index + 1}`}>
            {message.id}
            <div
              className={
                message.senderId === currentUserId
                  ? 'bubble-left-container'
                  : 'bubble-right-container'
              }
            >
              <div>
                {message.senderId === currentUserId && (
                  <img
                    src={`${message.avatar}`}
                    className="user-avatar"
                    alt="face"
                  />
                )}
              </div>
              {message.hasFile && (
                <FontAwesomeIcon icon={faFolderOpen} className="file-icon" />
              )}
              <p
                className={getBubbleClassName(message)}
                dangerouslySetInnerHTML={{
                  __html:
                    typeof message.content === 'string'
                      ? message.content.replace(/\n/g, '<br />')
                      : '',
                }}
              />
            </div>
            <p
              className={
                message.senderId === currentUserId ? 'time-left' : 'time-right'
              }
            >
              시분초 들어갈 예정
            </p>
          </div>
        ))}
      </div>
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
          <div className="ml-10 mr-10">예정1</div>
          <div className="ml-10 mr-10">예정2</div>
          <div className="ml-10 mr-10">예정3</div>
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

export default ChattingRoom;
