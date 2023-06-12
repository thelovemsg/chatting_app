import Accordion from 'react-bootstrap/Accordion';
import { useDispatch, useSelector } from 'react-redux';
import ProfileModal from 'component/utilComponent/ProfileModal';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import { useState, useEffect } from 'react';
import { GET_USER_UPDATE_FRIENDS_REQUEST } from 'reducers/user/userUpdatedFriends';
import { StyledAccordionBody } from '../../../styled-components/StyledForm';

const UpdatedFriend = () => {
  const dispatch = useDispatch();
  const { list } = useSelector((state) => state.user.updateFriend);
  console.log('list :: ', list);

  const [selectedUser, setSelectedUser] = useState(null);

  const handleCloseUserModal = () => {
    setSelectedUser(null);
  };

  useEffect(() => {
    dispatch(GET_USER_UPDATE_FRIENDS_REQUEST());
  }, [dispatch]);

  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline">
        <Accordion.Item eventKey="0">
          <Accordion.Header>UpdatedFriend</Accordion.Header>
          <StyledAccordionBody>
            <div>UpdatedFriend test...</div>
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
