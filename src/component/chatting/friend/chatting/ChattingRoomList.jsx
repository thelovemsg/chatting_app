import CommonProfileModalContent from 'component/utilComponent/modal/CommonProfileModalContent';
import ProfileModal from 'component/utilComponent/modal/ProfileModal';
import { returnCurrDateYYYYMMDDV2 } from 'component/utility/DateUtil';
import { sortFriendsByDate } from 'component/utility/SortUtil';
import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import PropTypes from 'prop-types';
import { GET_USER_CHATTING_ROOM_REQUEST } from 'reducers/chatting/chattingReducer';

const ChattingRoomList = ({ searchInput }) => {
  const dispatch = useDispatch();
  const [selectedUser, setSelectedUser] = useState(null);

  useEffect(() => {
    dispatch(GET_USER_CHATTING_ROOM_REQUEST());
  }, []);

  const handleUserClick = (user) => {
    setSelectedUser(user);
  };

  const handleCloseUserModal = () => {
    setSelectedUser(null);
  };

  const { list: chattingRoomList } = useSelector(
    (state) => state.user.chattingRoom
  );

  const sortList = sortFriendsByDate(chattingRoomList);

  const filteredChattingRoomList = sortList.filter((friend) =>
    friend.name.toLowerCase().includes(searchInput.toLowerCase())
  );

  return (
    <>
      {filteredChattingRoomList.length !== 0 && (
        <>
          {filteredChattingRoomList.map((chattingRoom, index) => (
            <div key={chattingRoom.id} className="profile-friend-box">
              <img
                src={chattingRoom.avatar}
                alt={`Friend ${index + 1}`}
                onClick={() => handleUserClick(chattingRoom)}
                className="profile-intro"
                aria-hidden="true"
              />
              <div className="custom-ml-20 custom-between">
                <div>
                  <div
                    role="button"
                    className="profile-label"
                    tabIndex={0}
                    onKeyDown={() => {}}
                    onClick={() => alert('test')}
                  >
                    {chattingRoom.name}
                  </div>
                  <div>
                    {chattingRoom.lastChattingContent.length > 40
                      ? `${chattingRoom.lastChattingContent.substr(0, 45)} ...`
                      : chattingRoom.lastChattingContent}
                  </div>
                </div>
                <div style={{ marginBottom: '20px', fontSize: '15px' }}>
                  {returnCurrDateYYYYMMDDV2(chattingRoom.lastChattingDate)}
                </div>
              </div>
            </div>
          ))}
        </>
      )}
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

export default ChattingRoomList;

ChattingRoomList.propTypes = {
  searchInput: PropTypes.string,
};

ChattingRoomList.defaultProps = {
  searchInput: null,
};
