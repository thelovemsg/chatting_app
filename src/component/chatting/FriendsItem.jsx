import { Accordion } from 'react-bootstrap';
import { useMemo, useState } from 'react';
import { StyledChattingItemNoPadding } from 'styled-components/StyledForm';
import { createRandomUser } from 'component/utility/FakeUser';
import DraggableModal from 'component/utilComponent/ProfileModal';
import DraggableModalContent from 'component/utilComponent/CommonProfileModalContent';

const FriendsItem = () => {
  const fakeUsers = useMemo(
    () => Array.from({ length: 10 }, () => createRandomUser()),
    []
  );

  const [selectedUser, setSelectedUser] = useState(null);

  const handleAvatarClick = (user) => {
    setSelectedUser(user);
  };

  const handleCloseModal = () => {
    setSelectedUser(null);
  };

  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline">
        <Accordion.Item eventKey="0">
          <Accordion.Header>Friend</Accordion.Header>
          {fakeUsers.map((user, index) => (
            <StyledChattingItemNoPadding key={user.userId}>
              <Accordion.Body
                style={{
                  marginLeft: '0px',
                  display: 'flex',
                  justifyContent: 'center',
                  alignItems: 'center',
                }}
              >
                <img
                  src={user.avatar}
                  alt={`Friend ${index + 1}`}
                  onClick={() => handleAvatarClick(user)}
                  className="my-profile-intro"
                  aria-hidden="true"
                />
                <div className="custom-ml-30">
                  <div className="profile-label">{user.username}</div>
                  <div className="profile-description custom-mt-5">
                    {`${user.userId} ${user.email}`}
                  </div>
                </div>
              </Accordion.Body>
            </StyledChattingItemNoPadding>
          ))}
        </Accordion.Item>
      </Accordion>
      {selectedUser && (
        <DraggableModal show>
          <DraggableModalContent
            handleCloseModal={handleCloseModal}
            userInfo={selectedUser}
            stateContent="상태명 드러감"
            footerContent="test"
          />
        </DraggableModal>
      )}
    </>
  );
};

export default FriendsItem;
