import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";

const Footer = () => {
  return (
    <Navbar
      bg="dark"
      expand="lg"
      fixed="bottom"
      className="color-yellow-first footer-height"
    >
      <Container>
        <Nav className="color-yellow-first" style={{ margin: "auto" }}>
          {/* copyRight(since 2023-03 ~ ) :  */}
          COPYRIGHT - 공대키메라
        </Nav>
        {/* <Nav className="color-yellow-first" style={{ margin: "auto" }}>
          <FontAwesomeIcon
            icon={faGitAlt}
            style={{ size: "200px", marginTop: "5px" }}
          />
          <span style={{ marginLeft: "20px" }}>thelovemsg</span>
        </Nav> */}
      </Container>
    </Navbar>
  );
};

export default Footer;
