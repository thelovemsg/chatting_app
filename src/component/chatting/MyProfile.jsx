import {
  faMagnifyingGlass,
  faUserGroup,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';

const MyProfile = () => (
  <div className="underline">
    <div
      className="intro-label"
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
      }}
    >
      <div style={{ float: 'left' }}>친구</div>
      <div style={{ marginRight: '10px' }}>
        <FontAwesomeIcon
          icon={faMagnifyingGlass}
          style={{ marginLeft: '30px' }}
        />
        <FontAwesomeIcon icon={faUserGroup} style={{ marginLeft: '30px' }} />
      </div>
    </div>
    <div>
      <div className="display-flex-items profile-background-color">
        <img
          src="./img/kakaoFriends.png"
          alt="First slide"
          className="my-profile-intro"
        />
        <div className="custom-ml-20">
          <div className="profile-label">MSG(선준)</div>
          <div className="profile-description custom-mt-5">
            여기에 뭐가 들어가야해요
          </div>
        </div>
      </div>
    </div>
  </div>
);

export default MyProfile;
