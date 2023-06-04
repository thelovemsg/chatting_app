import { faImage } from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PropTypes from 'prop-types';
import {
  faGear,
  faPen,
  faTrash,
  faUser,
  faPeopleGroup,
} from '@fortawesome/free-solid-svg-icons';
import imageUrlPropType from 'function/CheckImageUrlUtil';

const CommonProfileModalContent = ({
  handleCloseModal,
  userInfo,
  stateContent,
  showImageIcon,
  showSettingIcon,
  showDeleteIcon,
}) => {
  // const fakeUsers = useMemo(
  //   () => Array.from({ length: 1 }, () => createRandomUser()),
  //   []
  // );

  const handleImageClick = () => {
    console.log('handleImageClick !!');
  };

  const handleSettingClick = () => {
    console.log('handleSettingClick !!');
  };

  const handleProfileDelete = () => {
    console.log('handleProfileDelete !!');
  };

  return (
    <>
      <div className="modal-header">
        <h5 className="modal-title">
          {showImageIcon && (
            <FontAwesomeIcon
              icon={faImage}
              className="pointer"
              style={{ marginRight: '10px' }}
              onClick={handleImageClick()}
            />
          )}
          {showSettingIcon && (
            <FontAwesomeIcon
              icon={faGear}
              className="pointer"
              style={{ marginRight: '7px' }}
              onClick={handleSettingClick()}
            />
          )}
          {showDeleteIcon && (
            <FontAwesomeIcon
              icon={faTrash}
              className="pointer"
              onClick={handleProfileDelete()}
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
          {userInfo?.mainAvatar ? (
            <img
              src={userInfo.mainAvatar}
              alt="Selected Avatar"
              className="pointer"
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
        <button type="button">
          <FontAwesomeIcon icon={faPen} />
        </button>
        <button type="button">
          <FontAwesomeIcon icon={faPeopleGroup} />
        </button>
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
    mainAvatar: imageUrlPropType,
  }).isRequired,
  stateContent: PropTypes.string.isRequired,
  showImageIcon: PropTypes.bool,
  showDeleteIcon: PropTypes.bool,
  showSettingIcon: PropTypes.bool,
};

CommonProfileModalContent.defaultProps = {
  showImageIcon: false,
  showDeleteIcon: false,
  showSettingIcon: false,
};
