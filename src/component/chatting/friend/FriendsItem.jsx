import { Accordion } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import ProfileModal from 'component/utilComponent/ProfileModal';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import { useDispatch, useSelector } from 'react-redux';
import { GET_USER_FRIENDS_REQUEST } from 'reducers/user/userFriends';
import { StyledAccordionBody } from 'styled-components/StyledForm';
import { Trans } from 'react-i18next';

const FriendsItem = () => {
  const dispatch = useDispatch();
  const [selectedUser, setSelectedUser] = useState(null);

  useEffect(() => {
    dispatch(GET_USER_FRIENDS_REQUEST());
  }, []);

  const { list: friends } = useSelector((state) => state.user.friends);

  const sortedFriends = [...friends].sort((a, b) => {
    const targetA = a.name.charAt(0);
    const targetB = b.name.charAt(0);
    return targetA > targetB ? 1 : -1;
  });

  const handleUserClick = (user) => {
    setSelectedUser(user);
  };

  const handleCloseUserModal = () => {
    setSelectedUser(null);
  };

  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline">
        <Accordion.Item eventKey="0">
          <Accordion.Header>
            <Trans i18nKey="friend.title" className="profile-title" />
            <div className="custom-ml-10 ">{sortedFriends.length}</div>
          </Accordion.Header>
          <StyledAccordionBody>
            {sortedFriends.length !== 0 && (
              <>
                {sortedFriends.map((friend, index) => (
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
            showImageIcon
            showBookmark
            showProfileRotate
          />
        </ProfileModal>
      )}
    </>
  );
};

export default FriendsItem;
