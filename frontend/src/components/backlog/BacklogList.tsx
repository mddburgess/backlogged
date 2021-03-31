import { useEffect } from "react";
import { ListGroup } from "react-bootstrap";
import { connect, ConnectedProps } from "react-redux";

import { StoreState } from "../../store";
import { actions } from "../../store/backlog";

import BacklogRow from "./BacklogRow";

const mapStateToProps = (state: StoreState) => ({
  backlogItems: state.backlog.data,
});
const mapDispatchToProps = {
  listBacklog: actions.list,
};
const connector = connect(mapStateToProps, mapDispatchToProps);

type Props = ConnectedProps<typeof connector>;

const BacklogList = ({ backlogItems, listBacklog }: Props) => {
  useEffect(() => {
    void listBacklog();
  }, [listBacklog]);

  return (
    <ListGroup>
      {backlogItems.map((item) => (
        <BacklogRow key={item.key} backlog={item} />
      ))}
    </ListGroup>
  );
};

export default connector(BacklogList);
