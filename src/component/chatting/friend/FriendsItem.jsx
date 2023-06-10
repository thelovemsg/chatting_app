import { Accordion } from 'react-bootstrap';
import { useState } from 'react';
// import { StyledChattingItemNoPadding } from 'styled-components/StyledForm';
// import { createRandomUser } from 'component/utility/FakeUser';
import ProfileModal from 'component/utilComponent/ProfileModal';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import { useSelector } from 'react-redux';
// import { GET_USER_FRIENDS_REQUEST } from 'reducers/user/userFriends';

const FriendsItem = () => {
  // const fakeUsers = useMemo(
  //   () => Array.from({ length: 10 }, () => createRandomUser()),
  //   []
  // );

  const [selectedUser, setSelectedUser] = useState(null);
  // const dispatch = useDispatch();

  // useEffect(() => {
  //   dispatch(GET_USER_FRIENDS_REQUEST());
  // }, []);

  const { friends } = useSelector((state) => state.user.friends);
  console.log(friends);

  // const handleAvatarClick = (user) => {
  //   setSelectedUser(user);
  // };

  const handleCloseModal = () => {
    setSelectedUser(null);
  };

  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline">
        <Accordion.Item eventKey="0">
          <Accordion.Header>Friend</Accordion.Header>
          test 입니다.
          {/* {fakeUsers.map((user, index) => (
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
                  className="profile-intro"
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
          ))} */}
        </Accordion.Item>
      </Accordion>
      {selectedUser && (
        <ProfileModal show style={{ width: '300px', height: '600px' }}>
          <CommonProfileModalContent
            handleCloseModal={handleCloseModal}
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

export default FriendsItem;
