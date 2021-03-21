import React, { useEffect, useState } from "react";
import { updateTitle } from "../store/library";
import { connect, ConnectedProps } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import LibraryTitleForm from "./LibraryTitleForm";
import { Container } from "react-bootstrap";
import { api } from "../api";
import { Title } from "../types/Title";

const EditLibraryItem = ({ updateTitle }: ReduxProps) => {
  const history = useHistory();
  const params = useParams<{ key: string }>();

  const [title, setTitle] = useState<Title>({
    name: "",
    copies: [],
  });

  useEffect(() => {
    const fetchTitle = async () => {
      const result = await api.titles.retrieve(params.key);
      setTitle(result);
    };
    fetchTitle();
  }, [params.key]);

  return (
    <Container>
      <h1>Edit Library Title</h1>
      <LibraryTitleForm
        initialValues={title}
        onSubmit={(values) => {
          updateTitle(values);
          history.push("/");
        }}
      />
    </Container>
  );
};

const mapDispatchToProps = {
  updateTitle,
};

const connector = connect(undefined, mapDispatchToProps);

type ReduxProps = ConnectedProps<typeof connector>;

export default connector(EditLibraryItem);
