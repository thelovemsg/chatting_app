import Accordion from 'react-bootstrap/Accordion';

const BirthdayFriend = () => (
  <Accordion defaultActiveKey="0" className="custom-accordion underline">
    <Accordion.Item eventKey="0">
      <Accordion.Header>BirthdayFriend</Accordion.Header>
      <Accordion.Body>
        <div>BirthdayFriend test...</div>
      </Accordion.Body>
    </Accordion.Item>
  </Accordion>
);

export default BirthdayFriend;
