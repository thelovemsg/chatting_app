import Accordion from 'react-bootstrap/Accordion';

const MutiProfile = () => {
    return <> 
    <Accordion defaultActiveKey="0">
      <Accordion.Item eventKey="0">
        <Accordion.Header>test</Accordion.Header>
        <Accordion.Body>
          <div>MutiProfile test...</div>
        </Accordion.Body>
      </Accordion.Item>
    </Accordion>
    </>
}

export default MutiProfile
;