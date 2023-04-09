import Accordion from 'react-bootstrap/Accordion';

const UpdatedFriend = () => (
  <Accordion defaultActiveKey="0" className="custom-accordion underline">
    <Accordion.Item eventKey="0">
      <Accordion.Header>UpdatedFriend</Accordion.Header>
      <Accordion.Body>
        <div>UpdatedFriend test...</div>
      </Accordion.Body>
    </Accordion.Item>
  </Accordion>
);

export default UpdatedFriend;
