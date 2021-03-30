import React, { useEffect } from "react";
import { connect, ConnectedProps } from "react-redux";
import TitleRow from "./TitleRow";
import { listTitles } from "../../store/library";
import { Link } from "react-router-dom";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import { StoreState } from "../../store";

const TitleList = ({ titles, listTitles }: ReduxProps) => {
  useEffect(() => {
    listTitles();
  }, [listTitles]);

  return (
    <ListGroup>
      {titles.map((title) => (
        <TitleRow key={title.key} title={title} />
      ))}
      <ListGroupItem>
        <Link to="/new">Add item</Link>
      </ListGroupItem>
    </ListGroup>
  );
};

const mapStateToProps = (state: StoreState) => ({
  titles: state.library.data,
});

const mapDispatchToProps = {
  listTitles,
};

const connector = connect(mapStateToProps, mapDispatchToProps);

type ReduxProps = ConnectedProps<typeof connector>;

export default connector(TitleList);
