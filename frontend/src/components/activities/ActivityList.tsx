import { useEffect } from "react";
import { ListGroup } from "react-bootstrap";
import { connect, ConnectedProps } from "react-redux";

import { StoreState } from "../../store";
import { actions } from "../../store/activities";

import ActivityRow from "./ActivityRow";

const mapStateToProps = (state: StoreState) => ({
  activities: state.activities.data,
});
const mapDispatchToProps = {
  listActivities: actions.list,
};
const connector = connect(mapStateToProps, mapDispatchToProps);
type ReduxProps = ConnectedProps<typeof connector>;

const ActivityList = ({ activities, listActivities }: ReduxProps) => {
  useEffect(() => {
    void listActivities();
  }, [listActivities]);

  return (
    <ListGroup>
      {activities.map((activity) => (
        <ActivityRow activity={activity} />
      ))}
    </ListGroup>
  );
};

export default connector(ActivityList);
