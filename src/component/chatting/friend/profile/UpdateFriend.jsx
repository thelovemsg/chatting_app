import Accordion from 'react-bootstrap/Accordion';
import { useDispatch, useSelector } from 'react-redux';
import ProfileModal from 'component/utilComponent/modal/ProfileModal';
import CommonProfileModalContent from 'component/utilComponent/modal/CommonProfileModalContent';
import { useState, useEffect } from 'react';
import { GET_USER_UPDATE_FRIENDS_REQUEST } from 'reducers/user/userUpdatedFriends';
import { StyledAccordionBody } from 'styled-components/StyledForm';
import { Trans } from 'react-i18next';

const UpdatedFriend = () => {
  const dispatch = useDispatch();
  const { list: updateFriends } = useSelector(
    (state) => state.user.updateFriend
  );

  const [selectedUser, setSelectedUser] = useState(null);

  const handleUserClick = (user) => {
    setSelectedUser(user);
  };

  const handleCloseUserModal = () => {
    setSelectedUser(null);
  };

  useEffect(() => {
    dispatch(GET_USER_UPDATE_FRIENDS_REQUEST());
  }, []);

  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline">
        <Accordion.Item eventKey="0">
          <Accordion.Header>
            <Trans i18nKey="friend.update.title" />
          </Accordion.Header>
          <StyledAccordionBody>
            {updateFriends.length !== 0 && (
              <>
                {updateFriends.map((friend, index) => (
                  <div key={friend.id} className="profile-friend-box">
                    <img
                      src={friend.avatar}
                      alt={`Friend ${index + 1}`}
                      onClick={() => handleUserClick(friend)}
                      className="profile-intro"
                      aria-hidden="true"
                    />
                    <div className="custom-ml-10 custom-between">
                      <div>
                        <div className="profile-label">{friend.name}</div>
                      </div>
                    </div>
                  </div>
                ))}
              </>
            )}
          </StyledAccordionBody>
        </Accordion.Item>
      </Accordion>
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
export default UpdatedFriend;
