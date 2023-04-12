import { Accordion } from 'react-bootstrap';
import { faker } from '@faker-js/faker';
import { StyledChattingItemNoPadding } from '../../styled-components/StyledForm';

const FriendsItem = () => {
  const fakeImages = Array.from({ length: 10 }, () => faker.image.imageUrl());
  return (
    <Accordion defaultActiveKey="0" className="custom-accordion underline">
      <Accordion.Item eventKey="0">
        <Accordion.Header>Friend</Accordion.Header>
        {fakeImages.map((image, index) => (
          <StyledChattingItemNoPadding>
            <Accordion.Body
              style={{
                marginLeft: '0px',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
              }}
            >
              <img
                src={image}
                alt={`Friend ${index + 1}`}
                className="my-profile-intro"
              />
              <div className="custom-ml-30">
                <div className="profile-label">MSG(선준)</div>
                <div className="profile-description custom-mt-5">
                  여기에 뭐가 들어가야해요
                </div>
              </div>
            </Accordion.Body>
          </StyledChattingItemNoPadding>
        ))}
      </Accordion.Item>
    </Accordion>
  );
};

export default FriendsItem;
