import Accordion from 'react-bootstrap/Accordion';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCakeCandles } from '@fortawesome/free-solid-svg-icons';
import { Trans } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import { GET_USER_BIRTHDAY_FRIENDS_REQUEST } from 'reducers/user/userBirthdayFriends';
import ProfileModal from 'component/utilComponent/ProfileModal';
import BirthdayFriendsList from 'component/chatting/friend/birthday/BirthdayFriendsList';
import { StyledAccordionBody } from '../../../../styled-components/StyledForm';

const BirthdayFriend = () => {
  const dispatch = useDispatch();
  const { length } = useSelector((state) => state.user.birthday);
  const [openBirthdayFrinds, setOpenBirthdayFrinds] = useState(null);

  useEffect(() => {
    dispatch(GET_USER_BIRTHDAY_FRIENDS_REQUEST());
  }, [dispatch]);

  const handleBirthIconClick = () => {
    console.log('hello');
    setOpenBirthdayFrinds(true);
  };

  const handleCloseModal = () => {
    setOpenBirthdayFrinds(false);
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
        <ProfileModal show style={{ width: '340px', height: '510px' }}>
          <BirthdayFriendsList handleCloseModal={handleCloseModal} />
        </ProfileModal>
      )}
    </>
  );
};
export default BirthdayFriend;
