import { Container, Nav, Navbar } from 'react-bootstrap';

const Footer = () => (
  <Navbar
    bg="dark"
    expand="lg"
    fixed="bottom"
    className="color-yellow-first footer-height"
  >
    <Container>
      <Nav className="color-yellow-first" style={{ margin: 'auto' }}>
        COPYRIGHT - 공대키메라
      </Nav>
    </Container>
  </Navbar>
);

export default Footer;
