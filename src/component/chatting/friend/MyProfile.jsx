import {
  faMagnifyingGlass,
  faUser,
  faUserGroup,
  faXmark,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState, useEffect } from 'react';
import {
  StyledChattingIntroLabel,
  StyledChattingItem,
} from 'styled-components/StyledForm';
import { Trans } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import ProfileModal from 'component/utilComponent/ProfileModal';
import { SET_USER_PROFILE_REQUEST } from 'reducers/user/userProfile';

const MyProfile = () => {
  const { profile } = useSelector((state) => state.user);
  const [showModal, setShowModal] = useState(false);
  const [showSearchbox, setShowSearchBox] = useState(false);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(SET_USER_PROFILE_REQUEST());
  }, []);

  const handleSearchbox = () => {
    setShowSearchBox(!showSearchbox);
  };

  const handleSearchBoxClose = () => {
    setShowSearchBox(false);
  };

  const handleAvatarClick = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <div className="underline">
      <StyledChattingIntroLabel>
        <div className="main-profile-title">
          <Trans i18nKey="friend.title" />
        </div>
        <div className="custom-mr-10 flex-align-items-center">
          <FontAwesomeIcon
            className="cursor-pointer custom-mr-30 w-20 h-30"
            icon={faMagnifyingGlass}
            onClick={() => {
              handleSearchbox();
            }}
          />
          <FontAwesomeIcon
            icon={faUserGroup}
            style={{ width: '25px', height: '25px' }}
            onClick={() => {
              alert('friend add click!!');
            }}
          />
        </div>
      </StyledChattingIntroLabel>
      <div>
        {showSearchbox && (
          <div style={{ padding: '10px', width: '100%' }}>
            <FontAwesomeIcon
              icon={faMagnifyingGlass}
              className="profile-search-icon"
            />
            <input className="profile-search" type="text" />
            <FontAwesomeIcon
              icon={faXmark}
              className="custom-ml-10 w-20 h-20 cursor-pointer"
              onClick={() => {
                handleSearchBoxClose();
              }}
            />
          </div>
        )}
        <StyledChattingItem>
          {profile.info.avatar ? (
            <img
              src={profile.info?.avatar}
              alt="First slide"
              className="main-profile"
              onClick={() => handleAvatarClick()}
              aria-hidden="true"
            />
          ) : (
            <FontAwesomeIcon
              icon={faUser}
              className="profile-no-image"
              onClick={() => handleAvatarClick()}
            />
          )}
          <div>
            <div className="profile-label custom-ml-10">
              {profile.info?.name}
            </div>
            <div className="profile-description">
              {profile.info?.description}
            </div>
          </div>
        </StyledChattingItem>
      </div>
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
