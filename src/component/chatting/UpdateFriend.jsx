import Accordion from 'react-bootstrap/Accordion';
import { StyledAccordionBody } from '../../styled-components/StyledForm';

const UpdatedFriend = () => (
  <Accordion defaultActiveKey="0" className="custom-accordion underline">
    <Accordion.Item eventKey="0">
      <Accordion.Header>UpdatedFriend</Accordion.Header>
      <StyledAccordionBody>
        <div>UpdatedFriend test...</div>
      </StyledAccordionBody>
    </Accordion.Item>
  </Accordion>
);

export default UpdatedFriend;
