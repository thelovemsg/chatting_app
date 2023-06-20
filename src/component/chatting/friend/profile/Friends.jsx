import {
  faUser,
  faCommentDots,
  faBell,
  faEllipsis,
} from '@fortawesome/free-solid-svg-icons';

import {
  StyleFontAwesomeIcon,
  StyledChattingScreen,
  StyledChattingScreenIconsDown,
  StyledChattingScreenIconsTop,
  StyledChattingScreenLeft,
  StyledChattingScreenRight,
} from 'styled-components/StyledForm';
import { useState } from 'react';
import { FLIP_USER_NOTI_STATUS_REQUEST } from 'reducers/user/userInfoSetting';
import { useDispatch, useSelector } from 'react-redux';
import MyProfile from './MyProfile';
import MutiProfile from './MutiProfile';
import BirthdayFriend from '../birthday/BirthdayFriend';
import UpdatedFriend from './UpdateFriend';
import FriendsItem from './FriendsItem';
import ChattingList from '../chatting/ChattingList';

const Friends = () => {
  const dispatch = useDispatch();
  const { notiStatus } = useSelector((state) => state.user.info);
  const [showSearchBox, setShowSearchBox] = useState(false);
  const [searchInput, setSearchInput] = useState('');
  const [activeScreen, setActiveScreen] = useState('profile');

  const handleSearchBox = () => {
    setShowSearchBox(!showSearchBox);
  };

  const handleSearchInputChange = (e) => {
    setSearchInput(e.target.value);
  };

  const handleSearchBoxClose = () => {
    setShowSearchBox(false);
    setSearchInput('');
  };

  const handleProfileIconClick = () => {
    setActiveScreen('profile');
    setSearchInput('');
  };

  const handleChattingIconClick = () => {
    setActiveScreen('chatting');
    setSearchInput('');
  };

  const handleSettingClick = () => {
    setActiveScreen('setting');
    setSearchInput('');
  };

  const handleBellClick = () => {
    dispatch(FLIP_USER_NOTI_STATUS_REQUEST(!notiStatus));
  };

  const screenMap = {
    profile: (
      <>
        <MyProfile
          showSearchBox={showSearchBox}
          handleSearchBox={handleSearchBox}
          handleSearchBoxClose={handleSearchBoxClose}
          searchInput={searchInput}
          handleSearchInputChange={handleSearchInputChange}
        />
        {searchInput === '' && (
          <>
            <MutiProfile />
            <BirthdayFriend />
            <UpdatedFriend />
          </>
        )}
        <FriendsItem searchInput={searchInput} />
      </>
    ),
    chatting: (
      <>
        <ChattingList
          showSearchBox={showSearchBox}
          handleSearchBox={handleSearchBox}
          handleSearchBoxClose={handleSearchBoxClose}
          searchInput={searchInput}
          handleSearchInputChange={handleSearchInputChange}
        />
        <div>ㅎㅎ... 채팅방 클릭시</div>
      </>
    ),
    setting: <div>setting screen ggg..</div>,
  };

  return (
    <StyledChattingScreen>
      <StyledChattingScreenLeft>
        <StyledChattingScreenIconsTop>
          <StyleFontAwesomeIcon
            icon={faUser}
            onClick={() => {
              handleProfileIconClick();
            }}
          />
          <StyleFontAwesomeIcon
            icon={faCommentDots}
            onClick={() => {
              handleChattingIconClick();
            }}
          />
          <StyleFontAwesomeIcon
            icon={faEllipsis}
            onClick={() => {
              handleSettingClick();
            }}
          />
        </StyledChattingScreenIconsTop>
        <StyledChattingScreenIconsDown>
          <div className={`${notiStatus === false && 'no-noti-status'}`} />
          <StyleFontAwesomeIcon
            icon={faBell}
            onClick={() => {
              handleBellClick();
            }}
          />
        </StyledChattingScreenIconsDown>
      </StyledChattingScreenLeft>
      <StyledChattingScreenRight>
        {screenMap[activeScreen] || null}
      </StyledChattingScreenRight>
    </StyledChattingScreen>
  );
};
export default Friends;
