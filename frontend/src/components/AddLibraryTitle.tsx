import React from "react";
import { createTitle } from "../store/library";
import { connect, ConnectedProps } from "react-redux";
import { useHistory } from "react-router-dom";
import LibraryTitleForm from "./LibraryTitleForm";
import { Container } from "react-bootstrap";

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
          createTitle(title);
          history.push("/");
        }}
      />
    </Container>
  );
};

const mapDispatchToProps = {
  createTitle,
};

const connector = connect(undefined, mapDispatchToProps);

type ReduxProps = ConnectedProps<typeof connector>;

export default connector(AddLibraryTitle);
