import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { connect, ConnectedProps } from "react-redux";
import { useHistory, useParams } from "react-router-dom";

import { api } from "../api";
import { actions } from "../store/library";
import { Title } from "../types/Title";

import LibraryTitleForm from "./LibraryTitleForm";

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
    void fetchTitle();
  }, [params.key]);

  return (
    <Container>
      <h1>Edit Library Title</h1>
      <LibraryTitleForm
        initialValues={title}
        onSubmit={(values) => {
          void updateTitle(values);
          history.push("/");
        }}
      />
    </Container>
  );
};

const mapDispatchToProps = {
  updateTitle: actions.update,
};

const connector = connect(undefined, mapDispatchToProps);

type ReduxProps = ConnectedProps<typeof connector>;

export default connector(EditLibraryItem);
