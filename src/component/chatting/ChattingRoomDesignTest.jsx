import { createRandomChatMessage } from 'component/utility/FakeUser';
import React, { useState, useEffect, useRef } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faCalendar,
  faClock,
  faEnvelope,
  faFolderOpen,
  faPaperclip,
} from '@fortawesome/free-solid-svg-icons';
import '../../css/style.css';
import { useLocation } from 'react-router-dom';
import { returnDateToYYYYMMDD24HHMISS } from '../utility/DateUtil';

const ChattingRoom = () => {
  const location = useLocation();

  // 2. location.state 에서 파라미터 취득
  const { chattingRoomId } = location.state;
  const { userId } = location.state;

  console.log(chattingRoomId);
  console.log(userId);

  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');

  /**
   * TODO:
   * 로딩중이면 로딩 중이라고 표시하고 로딩에 실패하면 해당 정보를 전송함.
   */

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
    const newMessage = createRandomChatMessage(anotherUserId, currentUserId);
    const check = !!content;
    if (check) {
      newMessage.content = content;
      newMessage.hasFile = hasFile;
      newMessage.timestamp = new Date();
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
        chatWindowRef.current.style.maxHeight = `${window.innerHeight * 0.6}px`;
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
          <div
            className={
              message.senderId === currentUserId
                ? 'bubble-left-wrapper'
                : 'bubble-right-wrapper'
            }
            key={`${message.userId + index + 1}`}
          >
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
              {returnDateToYYYYMMDD24HHMISS(message.timestamp)}
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

export default ChattingRoom;
