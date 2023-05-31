import {
  faMagnifyingGlass,
  faUserGroup,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useState, useMemo } from 'react';
import {
  StyledChattingIntroLabel,
  StyledChattingItem,
} from 'styled-components/StyledForm';
import DraggableModal from 'component/utilComponent/ProfileModal';
import DraggableModalContent from 'component/utilComponent/CommonProfileModalContent';
import { createRandomUser } from 'component/utility/FakeUser';

const MyProfile = () => {
  const fakeUser = useMemo(() => createRandomUser(), []);
  const [showModal, setShowModal] = useState(false);

  const handleAvatarClick = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
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
          src={fakeUser.avatar}
          alt="First slide"
          className="my-profile-intro"
          onClick={() => handleAvatarClick()}
          aria-hidden="true"
        />
        <div className="custom-ml-30">
          <div className="profile-label">MSG(선준)</div>
          <div className="profile-description custom-mt-5">
            여기에 뭐가 들어가야해요
          </div>
        </div>
      </StyledChattingItem>
      <DraggableModal show={showModal}>
        <DraggableModalContent
          handleCloseModal={handleCloseModal}
          userInfo={fakeUser}
          stateContent="상태명 드러감"
          footerContent="test"
        />
      </DraggableModal>
    </div>
  );
};

export default MyProfile;
