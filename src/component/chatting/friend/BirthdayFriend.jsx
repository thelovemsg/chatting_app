import Accordion from 'react-bootstrap/Accordion';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCakeCandles } from '@fortawesome/free-solid-svg-icons';
import { Trans } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { GET_USER_BIRTHDAY_FRIENDS_REQUEST } from 'reducers/user/userBirthdayFriends';
import { StyledAccordionBody } from '../../../styled-components/StyledForm';

const BirthdayFriend = () => {
  const dispatch = useDispatch();
  const { length } = useSelector((state) => state.user.birthday);
  useEffect(() => {
    dispatch(GET_USER_BIRTHDAY_FRIENDS_REQUEST());
  }, [dispatch]);

  const handleAvatarClick = (user) => {
    // setSelectedUser(user);
    console.log(user);
    // const handleCloseModal = () => {
    //   setSelectedUser(null);
    // };
  };
  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline">
        <Accordion.Item eventKey="0">
          <Accordion.Header>
            <Trans i18nKey="friend.birthday.title" />
          </Accordion.Header>
          <StyledAccordionBody>
            <div className="birthday-box">
              <FontAwesomeIcon
                icon={faCakeCandles}
                onClick={handleAvatarClick()}
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
      {/* {selectedUser && (
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
    )} */}
    </>
  );
};
export default BirthdayFriend;
