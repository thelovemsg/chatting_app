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
import { createRandomUser } from 'component/utility/FakeUser';
import { Trans } from 'react-i18next';
import { useSelector } from 'react-redux';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import ProfileModal from 'component/utilComponent/ProfileModal';

const MyProfile = () => {
  const fakeUser = useMemo(() => createRandomUser(), []);
  const { profile } = useSelector((state) => state.user);
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
        <div style={{ float: 'left' }}>
          <Trans i18nKey="friend.friend" />
        </div>
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
          <div className="profile-label">{profile.info?.name}</div>
          <div className="profile-desc  ription custom-mt-5">
            {profile.info?.description}
          </div>
        </div>
      </StyledChattingItem>
      {showModal && (
        <ProfileModal
          show={showModal}
          style={{ width: '300px', height: '600px' }}
        >
          {profile?.info && (
            <CommonProfileModalContent
              handleCloseModal={handleCloseModal}
              userInfo={profile.info}
              stateContent="상태명 드러감"
              footerContent="버튼 2개 예정"
              multiProfileOption
            />
          )}
        </ProfileModal>
      )}
    </div>
  );
};

export default MyProfile;
