import { Accordion } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import ProfileModal from 'component/utilComponent/modal/ProfileModal';
import CommonProfileModalContent from 'component/utilComponent/modal/CommonProfileModalContent';
import { useDispatch, useSelector } from 'react-redux';
import { GET_USER_FRIENDS_REQUEST } from 'reducers/user/userFriends';
import { StyledAccordionBody } from 'styled-components/StyledForm';
import { Trans } from 'react-i18next';
import PropTypes from 'prop-types';
import { sortArrayWithFirstChar } from '../../../utility/SortUtil';
// component/utility/SortUtil';

const FriendsItem = ({ searchInput }) => {
  const dispatch = useDispatch();
  const [selectedUser, setSelectedUser] = useState(null);

  useEffect(() => {
    dispatch(GET_USER_FRIENDS_REQUEST());
  }, []);

  const { list: friends } = useSelector((state) => state.user.friends);

  const filteredFriends = friends.filter((friend) =>
    friend.name.toLowerCase().includes(searchInput.toLowerCase())
  );

  const sortedFriends = sortArrayWithFirstChar(filteredFriends);

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
                    <div className="custom-ml-20 custom-between">
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

FriendsItem.propTypes = {
  searchInput: PropTypes.string,
};

FriendsItem.defaultProps = {
  searchInput: null,
};
