import { Container } from "react-bootstrap";
import { connect, ConnectedProps } from "react-redux";
import { useHistory } from "react-router-dom";

import { actions } from "../store/library";

import LibraryTitleForm from "./LibraryTitleForm";

const AddLibraryTitle = ({ createTitle }: ReduxProps) => {
  const history = useHistory();

  return (
    <Container>
      <h1>Add Library Title</h1>
      <LibraryTitleForm
        initialValues={{
          name: "",
          copies: [
            {
              platform: "",
              service: "",
            },
          ],
        }}
        onSubmit={(title) => {
          void createTitle(title);
          history.push("/");
        }}
      />
    </Container>
  );
};

const mapDispatchToProps = {
  createTitle: actions.create,
};

const connector = connect(undefined, mapDispatchToProps);

type ReduxProps = ConnectedProps<typeof connector>;

export default connector(AddLibraryTitle);
