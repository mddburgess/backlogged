import { useEffect } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import { connect, ConnectedProps } from "react-redux";
import { Link } from "react-router-dom";

import { StoreState } from "../../store";
import { actions } from "../../store/titles";

import TitleRow from "./TitleRow";

const mapStateToProps = (state: StoreState) => ({
  titles: state.titles.list,
});
const mapDispatchToProps = {
  listTitles: actions.list,
};
const connector = connect(mapStateToProps, mapDispatchToProps);

type Props = ConnectedProps<typeof connector>;

const TitleList = ({ titles, listTitles }: Props) => {
  useEffect(() => {
    void listTitles();
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

export default connector(TitleList);
