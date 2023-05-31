import { faPlus } from '@fortawesome/free-solid-svg-icons';
import Accordion from 'react-bootstrap/Accordion';
import { Trans } from 'react-i18next';
import React, { useState, useMemo } from 'react';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import NewMultiProfileModalContent from 'component/utilComponent/NewMultiProfileModalContent';
import ProfileModal from '../utilComponent/ProfileModal';
import { createRandomUser } from '../utility/FakeUser';
import {
  StyledAccordionBodyMultiProfile,
  StyledFontAwesomeIconPlus,
} from '../../styled-components/StyledForm';

const MutiProfile = () => {
  /*
    multi profile은 3개 이상 존재하면 + 버튼은 사라지게 해야함.
  */

  /**
   * TODO
   * use useMemo hook in order to get avatars of user just one time.
   */

  const fakeUsers = useMemo(
    () => Array.from({ length: 2 }, () => createRandomUser()),
    []
  );

  const [showModal, setShowModal] = useState(false);
  const [showNewForm, setShowNewForm] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);

  const handleAvatarClick = (user) => {
    setSelectedUser(user);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleAddNewProfile = () => {
    setShowNewForm(true);
  };

  const handleCloseForm = () => {
    setShowNewForm(false);
  };

  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline ">
        <Accordion.Item eventKey="0">
          <Accordion.Header>
            <Trans i18nKey="friend.multi_profile" />
          </Accordion.Header>
          <StyledAccordionBodyMultiProfile>
            {fakeUsers.map((user, index) => (
              <button
                key={`${user.userId}`}
                className="avatar-button"
                onClick={() => handleAvatarClick(user)}
                aria-label={`Friend ${index + 1} ${user.userId}`}
                style={{
                  border: 'none',
                  background: 'transparent',
                  padding: 0,
                }}
                type="button"
              >
                <img
                  src={user.avatar}
                  alt={`Friend ${index + 9000} ${user.userId}`}
                  className="multi-profile-others"
                />
                <div className="multi-profile-name">test</div>
              </button>
            ))}
            {/* 여기 item들이 hover이벤트가 먹어야함. */}
            <button
              className="avatar-button"
              onClick={() => handleAddNewProfile('new')}
              style={{
                border: 'none',
                background: 'transparent',
                padding: 0,
              }}
              type="button"
            >
              <StyledFontAwesomeIconPlus
                icon={faPlus}
                className="multi-profile-add-button"
              />
              <div className="multi-profile-name">만들기</div>
            </button>
          </StyledAccordionBodyMultiProfile>
        </Accordion.Item>
      </Accordion>
      <ProfileModal
        show={showModal}
        style={{ width: '300px', height: '600px' }}
      >
        <CommonProfileModalContent
          handleCloseModal={handleCloseModal}
          userInfo={selectedUser}
          stateContent="상태명 드러감"
          footerContent="버튼 2개 예정"
        />
      </ProfileModal>
      <ProfileModal
        show={showNewForm}
        style={{ width: '340px', height: '510px' }}
      >
        <NewMultiProfileModalContent
          handleCloseModal={handleCloseForm}
          userInfo={null}
          stateContent="상태명 드러감"
          footerContent="test"
        />
      </ProfileModal>
    </>
  );
};

export default MutiProfile;
