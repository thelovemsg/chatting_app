import Accordion from 'react-bootstrap/Accordion';

const MutiProfile = () => (
  <Accordion defaultActiveKey="0" className="custom-accordion underline ">
    <Accordion.Item eventKey="0">
      <Accordion.Header>MutiProfile</Accordion.Header>
      <Accordion.Body>
        {/* 여기 item들이 hover이벤트가 먹어야함. */}
        <div>MutiProfile test...</div>
      </Accordion.Body>
    </Accordion.Item>
  </Accordion>
);

export default MutiProfile;
