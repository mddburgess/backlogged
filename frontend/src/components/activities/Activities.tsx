import { ListGroup } from "react-bootstrap";
import ActivityItem from "./ActivityItem";
import { StoreState } from "../../store";
import { connect, ConnectedProps } from "react-redux";
import React, { useEffect } from "react";
import { actions } from "../../store/activities";

const mapStateToProps = (state: StoreState) => ({
  activities: state.activities.data,
});
const mapDispatchToProps = {
  listActivities: actions.list,
};
const connector = connect(mapStateToProps, mapDispatchToProps);
type ReduxProps = ConnectedProps<typeof connector>;

const Activities = ({ activities, listActivities }: ReduxProps) => {
  useEffect(() => {
    void listActivities();
  }, [listActivities]);

  return (
    <ListGroup>
      {activities.map((activity) => (
        <ActivityItem activity={activity} />
      ))}
    </ListGroup>
  );
};

export default connector(Activities);
