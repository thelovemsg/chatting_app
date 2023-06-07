import { faImage } from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PropTypes from 'prop-types';
import {
  faGear,
  faPen,
  faTrash,
  faUser,
  faPeopleGroup,
  faStar,
  faRotate,
  faComment,
  faPhoneVolume,
  faVideo,
} from '@fortawesome/free-solid-svg-icons';
import imageUrlPropType from 'function/CheckImageUrlUtil';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import profileModalHandle from './func/ProfileModalHandle';

const CommonProfileModalContent = ({
  handleCloseModal,
  userInfo,
  stateContent,
  showImageIcon,
  showBookmark,
  showSettingIcon,
  showDeleteIcon,
  showProfileRotate,
  multiProfileOption,
}) => {
  const { success } = useSelector((state) => state.user.multiProfile);

  useEffect(() => {
    if (success) handleCloseModal();
  }, [success]);

  const {
    handleImageClick,
    handleBookmarkClick,
    handleSettingClick,
    handleProfileDelete,
    handleProfileRotate,
    handleOneToOneChat,
    handleVioceCall,
    handleVideoCall,
  } = profileModalHandle();

  return (
    <>
      <div className="modal-header">
        <h5 className="modal-title">
          {showImageIcon && (
            <FontAwesomeIcon
              icon={faImage}
              className="profile-icon"
              onClick={() => handleImageClick()}
            />
          )}
          {showBookmark && (
            <FontAwesomeIcon
              icon={faStar}
              className="profile-icon"
              onClick={() => handleBookmarkClick()}
            />
          )}
          {showSettingIcon && (
            <FontAwesomeIcon
              icon={faGear}
              className="profile-icon"
              onClick={() => handleSettingClick()}
            />
          )}
          {showDeleteIcon && (
            <FontAwesomeIcon
              icon={faTrash}
              className="profile-icon"
              onClick={() => handleProfileDelete(userInfo)}
            />
          )}
          {showProfileRotate && (
            <FontAwesomeIcon
              icon={faRotate}
              className="profile-icon"
              onClick={() => handleProfileRotate()}
            />
          )}
        </h5>
        <button
          type="button"
          className="btn-close"
          onClick={handleCloseModal}
          aria-label="hidden"
        />
      </div>
      <div
        className="modal-body"
        style={{ margin: 'auto', textAlign: 'center', width: '100%' }}
      >
        <div style={{ height: '67%', width: '100%', backgroundColor: 'red' }}>
          테스팅 영역입니다.
        </div>
        <div>
          {userInfo?.avatar ? (
            <img
              src={userInfo.avatar}
              alt="Selected Avatar"
              className="common-profile-img"
              onClick={() => handleImageClick()}
              aria-hidden="true"
            />
          ) : (
            <FontAwesomeIcon
              icon={faUser}
              className="common-profile-no-image"
            />
          )}
        </div>
        <div className="common-profile-name">{userInfo.name}</div>
        <div className="common-profile-description">{stateContent}</div>
      </div>
      <div className="modal-footer" style={{ height: '18%' }}>
        {multiProfileOption ? (
          <div className="profile-btns">
            <button type="button" className="profile-btn">
              <FontAwesomeIcon icon={faPen} />
              <div className="profile-btn-comment">나랑 채팅</div>
            </button>
            <button type="button" className="profile-btn">
              <FontAwesomeIcon icon={faPeopleGroup} />
              <div className="profile-btn-comment">친구 관리</div>
            </button>
          </div>
        ) : (
          <div className="profile-btns">
            <button
              type="button"
              className="profile-btn"
              onClick={handleOneToOneChat}
            >
              <FontAwesomeIcon icon={faComment} />
              <div className="profile-btn-comment">1:1 채팅</div>
            </button>
            <button
              type="button"
              className="profile-btn"
              onClick={handleVioceCall}
            >
              <FontAwesomeIcon icon={faPhoneVolume} />
              <div className="profile-btn-comment">통화</div>
            </button>
            <button
              type="button"
              className="profile-btn"
              onClick={handleVideoCall}
            >
              <FontAwesomeIcon icon={faVideo} />
              <div className="profile-btn-comment" o>
                페이스톡
              </div>
            </button>
          </div>
        )}
      </div>
    </>
  );
};
export default CommonProfileModalContent;

/**
 * the reason why we should use TypeScript!!
 */
CommonProfileModalContent.propTypes = {
  handleCloseModal: PropTypes.func.isRequired,
  userInfo: PropTypes.shape({
    email: PropTypes.string,
    name: PropTypes.string,
    description: PropTypes.string,
    avatar: imageUrlPropType,
  }).isRequired,
  stateContent: PropTypes.string.isRequired,
  showImageIcon: PropTypes.bool,
  showBookmark: PropTypes.bool,
  showDeleteIcon: PropTypes.bool,
  showSettingIcon: PropTypes.bool,
  showProfileRotate: PropTypes.bool,
  multiProfileOption: PropTypes.bool,
};

CommonProfileModalContent.defaultProps = {
  showImageIcon: false,
  showBookmark: false,
  showDeleteIcon: false,
  showSettingIcon: false,
  showProfileRotate: false,
  multiProfileOption: false,
};
