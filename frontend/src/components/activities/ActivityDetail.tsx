import { Link } from "react-router-dom";

import { Activity } from "../../types/Activity";

interface Props {
  activity: Activity;
}

const ActivityDetail = ({ activity }: Props) => {
  switch (activity.type) {
    case "ADD_TO_BACKLOG":
      return (
        <>
          You added <Link to={`/title/${activity.title.key}`}>{activity.title.name}</Link> to your
          backlog.
        </>
      );
    case "ADD_TO_LIBRARY":
      return (
        <>
          You added <Link to={`/title/${activity.title.key}`}>{activity.title.name}</Link> to your
          library.
        </>
      );
    case "REMOVE_FROM_BACKLOG":
      return (
        <>
          You removed <Link to={`/title/${activity.title.key}`}>{activity.title.name}</Link> from
          your backlog.
        </>
      );
    default:
      return <>You moved in mysterious ways.</>;
  }
};

export default ActivityDetail;
