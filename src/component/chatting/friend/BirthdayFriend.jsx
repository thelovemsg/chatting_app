import Accordion from 'react-bootstrap/Accordion';
import { StyledAccordionBody } from '../../../styled-components/StyledForm';

const BirthdayFriend = () => (
  <Accordion defaultActiveKey="0" className="custom-accordion underline">
    <Accordion.Item eventKey="0">
      <Accordion.Header>BirthdayFriend</Accordion.Header>
      <StyledAccordionBody>
        <div>BirthdayFriend test...</div>
      </StyledAccordionBody>
    </Accordion.Item>
  </Accordion>
);

export default BirthdayFriend;
