import {
  faMagnifyingGlass,
  faUserGroup,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';
import {
  StyledChattingIntroLabel,
  StyledChattingItem,
} from '../../styled-components/StyledForm';

const MyProfile = () => (
  <div className="underline">
    <StyledChattingIntroLabel>
      <div style={{ float: 'left' }}>친구</div>
      <div style={{ marginRight: '10px' }}>
        <FontAwesomeIcon icon={faMagnifyingGlass} className="custom-mr-30" />
        <FontAwesomeIcon icon={faUserGroup} />
      </div>
    </StyledChattingIntroLabel>
    <StyledChattingItem>
      <img
        src="./img/kakaoFriends.png"
        alt="First slide"
        className="my-profile-intro"
      />
      <div className="custom-ml-30">
        <div className="profile-label">MSG(선준)</div>
        <div className="profile-description custom-mt-5">
          여기에 뭐가 들어가야해요
        </div>
      </div>
    </StyledChattingItem>
  </div>
);

export default MyProfile;
