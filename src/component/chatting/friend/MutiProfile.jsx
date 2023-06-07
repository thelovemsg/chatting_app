import { faPlus, faUser } from '@fortawesome/free-solid-svg-icons';
import Accordion from 'react-bootstrap/Accordion';
import { Trans } from 'react-i18next';
import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import CommonProfileModalContent from 'component/utilComponent/CommonProfileModalContent';
import NewMultiProfileModalContent from 'component/utilComponent/NewMultiProfileModalContent';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import shortenWords from 'component/utility/UiUtil';
import ProfileModal from '../../utilComponent/ProfileModal';
// import { createRandomUser } from '../utility/FakeUser';
import {
  StyledAccordionBodyMultiProfile,
  StyledFontAwesomeIconPlus,
} from '../../../styled-components/StyledForm';

const MutiProfile = () => {
  // const fakeUsers = useMemo(
  //   () => Array.from({ length: 2 }, () => createRandomUser()),
  //   []
  // );

  const [showModal, setShowModal] = useState(false);
  const [showNewForm, setShowNewForm] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);
  const [profileLength, setProfileLength] = useState(0);

  const { list } = useSelector((state) => state.user.multiProfile);
  useEffect(() => {
    setProfileLength(list.length);
  }, [list]);

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
            {list.map((user, index) => (
              <button
                key={`${user?.userId}`}
                className="avatar-button"
                onClick={() => handleAvatarClick(user)}
                aria-label={`Friend ${index + 1} ${user?.userId}`}
                style={{
                  border: 'none',
                  background: 'transparent',
                  padding: 0,
                }}
                type="button"
              >
                {user.avatar ? (
                  <img
                    src={user.avatar}
                    alt={`Friend ${index + 9000} ${user?.userId}`}
                    className="multi-profile-others"
                  />
                ) : (
                  <FontAwesomeIcon
                    icon={faUser}
                    className="multi-profile-no-image"
                  />
                )}
                <div className="multi-profile-name">
                  {shortenWords(user.name)}
                </div>
              </button>
            ))}
            {/* 여기 item들이 hover이벤트가 먹어야함. */}
            {profileLength < 3 && (
              <button
                className="avatar-button"
                onClick={() => handleAddNewProfile()}
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
                <div className="multi-profile-name">
                  <Trans i18nKey="default.add" />
                </div>
              </button>
            )}
          </StyledAccordionBodyMultiProfile>
        </Accordion.Item>
      </Accordion>
      {showModal && (
        <ProfileModal
          show={showModal}
          style={{ width: '300px', height: '600px' }}
        >
          {selectedUser && (
            <CommonProfileModalContent
              handleCloseModal={handleCloseModal}
              userInfo={selectedUser}
              stateContent="상태명 드러감"
              footerContent="버튼 2개 예정"
              showImageIcon
              showSettingIcon
              showDeleteIcon
              multiProfileOption
            />
          )}
        </ProfileModal>
      )}
      {showNewForm && (
        <ProfileModal
          show={showNewForm}
          style={{ width: '340px', height: '510px' }}
        >
          <NewMultiProfileModalContent handleCloseModal={handleCloseForm} />
        </ProfileModal>
      )}
    </>
  );
};

export default MutiProfile;
