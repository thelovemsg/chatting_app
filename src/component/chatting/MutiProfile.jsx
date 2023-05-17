import { faPlus } from '@fortawesome/free-solid-svg-icons';
import Accordion from 'react-bootstrap/Accordion';
import { Trans } from 'react-i18next';
// import { Button } from 'react-bootstrap';
import React, { useState, useMemo } from 'react';
// import Draggable from 'react-draggable';
// import { propTypes } from 'react-bootstrap/esm/Image';
// import Resizable from 'resizable';
import { createRandomUser } from '../utility/FakeUser';
import {
  StyledAccordionBodyMultiProfile,
  StyledFontAwesomeIconPlus,
} from '../../styled-components/StyledForm';
import DraggableModal from '../utilComponent/DraggableModal';

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
  const [selectedAvatar, setSelectedAvatar] = useState(null);

  const handleAvatarClick = (avatar) => {
    setSelectedAvatar(avatar);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
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
                onClick={() => handleAvatarClick(user.avatar)}
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
                  className="profile-others"
                />
              </button>
            ))}
            {/* 여기 item들이 hover이벤트가 먹어야함. */}
            <StyledFontAwesomeIconPlus icon={faPlus} />
            <div>test</div>
          </StyledAccordionBodyMultiProfile>
        </Accordion.Item>
      </Accordion>
      <DraggableModal show={showModal}>
        <div className="modal-header">
          <h5 className="modal-title">Avatar</h5>
          <button
            type="button"
            className="btn-close"
            onClick={handleCloseModal}
            aria-label="hidden"
          />
        </div>
        <div
          className="modal-body"
          style={{ margin: 'auto', textAlign: 'center' }}
        >
          <div style={{ height: '60%' }}>test1</div>
          {selectedAvatar && <img src={selectedAvatar} alt="Selected Avatar" />}
          <div>상태명 드러감</div>
        </div>
        <div className="modal-footer">test</div>
      </DraggableModal>
    </>
  );
};

export default MutiProfile;
