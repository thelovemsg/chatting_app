import Accordion from 'react-bootstrap/Accordion';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCakeCandles } from '@fortawesome/free-solid-svg-icons';
import { Trans } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import { GET_USER_BIRTHDAY_FRIENDS_REQUEST } from 'reducers/user/userBirthdayFriends';
import ProfileModal from 'component/utilComponent/ProfileModal';
import BirthdayFriendsList from 'component/chatting/friend/birthday/BirthdayFriendsList';
import { compareWithCurrentDate, returnMMDD } from 'component/utility/DateUtil';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import { StyledAccordionBody } from '../../../../styled-components/StyledForm';

const BirthdayFriend = () => {
  const dispatch = useDispatch();
  const { length } = useSelector((state) => state.user.birthday);
  const [openBirthdayFrinds, setOpenBirthdayFrinds] = useState(null);
  const [selectedUser, setSelectedUser] = useState(null);

  const handleUserClick = (user) => {
    setSelectedUser(user);
  };

  const handleCloseUserModal = () => {
    setSelectedUser(null);
  };

  useEffect(() => {
    dispatch(GET_USER_BIRTHDAY_FRIENDS_REQUEST());
  }, [dispatch]);

  const handleBirthIconClick = () => {
    setOpenBirthdayFrinds(true);
  };

  const handleCloseBirthdayModal = () => {
    setOpenBirthdayFrinds(false);
  };

  const { list: birthdayFriends } = useSelector((state) => state.user.birthday);

  const currBirthdayFriends = birthdayFriends.filter(
    (val) => compareWithCurrentDate(val.birthdate) === 0
  );

  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline">
        <Accordion.Item eventKey="0">
          <Accordion.Header>
            <Trans i18nKey="friend.birthday.title" />
          </Accordion.Header>
          <StyledAccordionBody>
            <div className="birthday-box">
              {currBirthdayFriends.map((friend, index) => (
                <div
                  key={friend.id}
                  className="birthday-friend-box"
                  style={{
                    display: 'flex',
                    padding: '10px',
                    paddingLeft: '0px',
                    alignItems: 'center',
                    width: '100%',
                  }}
                >
                  <img
                    src={friend.avatar}
                    alt={`Friend ${index + 1}`}
                    onClick={() => handleUserClick(friend)}
                    className="profile-intro"
                    aria-hidden="true"
                  />
                  <div
                    className="custom-ml-10"
                    style={{
                      display: 'flex',
                      alignItems: 'center',
                      width: '100%',
                      justifyContent: 'space-between',
                    }}
                  >
                    <div className="profile-label">
                      <div>{returnMMDD()}</div>
                      <div>{friend.name}</div>
                    </div>
                    <div>
                      <button
                        type="button"
                        className="give-gift"
                        onClick={() => alert('나중에 작업할거에요 ㅎ')}
                      >
                        <Trans i18nKey="friend.birthday.giveGift" />
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </div>
            <div className="birthday-box">
              <FontAwesomeIcon
                icon={faCakeCandles}
                onClick={() => handleBirthIconClick()}
                className="birthday-icon"
              />
              <div className="custom-mr-20">
                <Trans i18nKey="friend.birthday.content" />
              </div>
              <div style={{ color: 'red' }}>{length}</div>
            </div>
          </StyledAccordionBody>
        </Accordion.Item>
      </Accordion>
      {openBirthdayFrinds && (
        <ProfileModal
          show
          style={{
            width: '340px',
            height: '510px',
            overflow: 'auto',
          }}
        >
          <BirthdayFriendsList handleCloseModal={handleCloseBirthdayModal} />
        </ProfileModal>
      )}
      {selectedUser && (
        <ProfileModal show style={{ width: '300px', height: '600px' }}>
          <CommonProfileModalContent
            handleCloseModal={handleCloseUserModal}
            userInfo={selectedUser}
            stateContent="상태명 드러감"
            showImageIcon
            showBookmark
            showProfileRotate
          />
        </ProfileModal>
      )}
    </>
  );
};
export default BirthdayFriend;
