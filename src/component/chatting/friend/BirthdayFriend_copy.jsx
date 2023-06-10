import Accordion from 'react-bootstrap/Accordion';
import { useDispatch, useSelector } from 'react-redux';
import { GET_USER_BIRTHDAY_FRIENDS_REQUEST } from 'reducers/user/userBirthdayFriends';
import { useEffect, useState } from 'react';
import ProfileModal from 'component/utilComponent/ProfileModal';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import { returnDateYYYYMMDD } from 'component/utility/DateUtil';
import {
  StyledAccordionBody,
  StyledChattingItemNoPadding,
} from '../../../styled-components/StyledForm';

const BirthdayFriend = () => {
  const dispatch = useDispatch();
  const { list: birthdayList } = useSelector((state) => state.user.birthday);
  const [selectedUser, setSelectedUser] = useState(null);

  useEffect(() => {
    dispatch(GET_USER_BIRTHDAY_FRIENDS_REQUEST());
  }, [dispatch]);

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
          <Accordion.Header>BirthdayFriend</Accordion.Header>
          <StyledAccordionBody>
            {birthdayList &&
              birthdayList.map((user, index) => {
                const { YYYYMMDD, difference } = returnDateYYYYMMDD(
                  user.registeredAt,
                  new Date()
                );
                return (
                  <StyledChattingItemNoPadding key={user.id}>
                    <Accordion.Body
                      style={{
                        marginLeft: '0px',
                        width: '700px',
                      }}
                    >
                      <div
                        style={{
                          display: 'flex',
                          justifyContent: 'space-between',
                          alignItems: 'center',
                        }}
                      >
                        <div className="profile-box">
                          <img
                            src={user.avatar}
                            alt={`Friend ${index + 1}`}
                            onClick={() => handleAvatarClick(user)}
                            className="profile-intro"
                            aria-hidden="true"
                          />
                          <div className="custom-ml-20">
                            <div className="profile-label">{user.name}</div>
                            <div className="profile-description custom-mt-5">
                              {difference}일전 {YYYYMMDD}
                            </div>
                          </div>
                        </div>
                        <button type="button" className="profile-send-present">
                          선물하기
                        </button>
                      </div>
                    </Accordion.Body>
                  </StyledChattingItemNoPadding>
                );
              })}
          </StyledAccordionBody>
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
export default BirthdayFriend;
