import { Accordion } from 'react-bootstrap';
import faker from '../../../node_modules/faker';

const FriendsItem = () => {
  const fakeImages = Array.from({ length: 10 }, () => faker.image.imageUrl());
  return (
    // const friendList = articles.map((article) => (
    //   <Article title={article.title} writer={article.writer} key={article.id} />
    // ));

    <Accordion defaultActiveKey="0" className="custom-accordion underline">
      <Accordion.Item eventKey="0">
        <Accordion.Header>Friend</Accordion.Header>
        {fakeImages.map((image, index) => (
          <Accordion.Body>
            <img src={image} alt={`Friend ${index + 1}`} />
          </Accordion.Body>
        ))}
      </Accordion.Item>
    </Accordion>
  );
};

export default FriendsItem;
