import {
  faMagnifyingGlass,
  faUser,
  faUserGroup,
  faXmark,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState, useEffect, useRef } from 'react';
import PropTypes from 'prop-types';
import {
  StyledChattingIntroLabel,
  StyledChattingItem,
} from 'styled-components/StyledForm';
import { Trans } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import CommonProfileModalContent from 'component/utilComponent/modal/CommonProfileModalContent';
import ProfileModal from 'component/utilComponent/modal/ProfileModal';
import { SET_USER_PROFILE_REQUEST } from 'reducers/user/userProfile';
import ProfileImageCarouselModal from 'component/utilComponent/modal/ProfileImageCarouselModal';

const MyProfile = ({
  showSearchBox,
  handleSearchBox,
  handleSearchBoxClose,
  searchInput,
  handleSearchInputChange,
}) => {
  const { profile } = useSelector((state) => state.user);
  const [showModal, setShowModal] = useState(false);
  const [isSecondModalOpen, setIsSecondModalOpen] = useState(false);

  // Declare your ref
  const searchInputRef = useRef(null);

  useEffect(() => {
    // Whenever the search box is shown, focus the input
    if (showSearchBox && searchInputRef.current) {
      searchInputRef.current.focus();
    }
  }, [showSearchBox]);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(SET_USER_PROFILE_REQUEST());
  }, []);

  const handleAvatarClick = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleSecondModalClose = () => {
    setIsSecondModalOpen(!isSecondModalOpen);
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
            onClick={handleSearchBox}
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
        {showSearchBox && (
          <div style={{ padding: '10px', width: '100%' }}>
            <FontAwesomeIcon
              icon={faMagnifyingGlass}
              className="profile-search-icon"
            />
            <input
              ref={searchInputRef}
              className="profile-search"
              type="text"
              value={searchInput}
              onChange={handleSearchInputChange}
            />
            <FontAwesomeIcon
              icon={faXmark}
              className="custom-ml-10 w-20 h-20 cursor-pointer"
              onClick={handleSearchBoxClose}
            />
          </div>
        )}
        {searchInput === '' && (
          <StyledChattingItem>
            {profile.info.avatar ? (
              <img
                src={profile.info?.avatar}
                alt="First slide"
                className="main-profile"
                onClick={handleAvatarClick}
                aria-hidden="true"
              />
            ) : (
              <FontAwesomeIcon
                icon={faUser}
                className="profile-no-image"
                onClick={() => handleAvatarClick}
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
        )}
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
              setIsSecondModalOpen={setIsSecondModalOpen}
              multiProfileOption
            />
          )}
        </ProfileModal>
      )}

      {isSecondModalOpen && (
        <ProfileModal
          show={isSecondModalOpen}
          style={{ width: '570px', height: '510px', borderRadius: 'none' }}
        >
          <ProfileImageCarouselModal
            handleCloseModal={handleSecondModalClose}
            imageList={profile.info}
          />
        </ProfileModal>
      )}
    </div>
  );
};

export default MyProfile;

MyProfile.propTypes = {
  showSearchBox: PropTypes.bool.isRequired,
  handleSearchBox: PropTypes.func.isRequired,
  handleSearchBoxClose: PropTypes.func.isRequired,
  searchInput: PropTypes.string,
  handleSearchInputChange: PropTypes.func.isRequired,
};

MyProfile.defaultProps = {
  searchInput: null,
};
