import { ActivityProps } from "../../types/Activity";
import TitleDetailsLink from "../titles/TitleDetailsLink";

const ActivityDetail = ({ activity }: ActivityProps) => {
  switch (activity.type) {
    case "ADD_TO_BACKLOG":
      return (
        <>
          You added <TitleDetailsLink title={activity.title} /> to your backlog.
        </>
      );
    case "ADD_TO_LIBRARY":
      return (
        <>
          You added <TitleDetailsLink title={activity.title} /> to your library.
        </>
      );
    case "REMOVE_FROM_BACKLOG":
      return (
        <>
          You removed <TitleDetailsLink title={activity.title} /> from your backlog.
        </>
      );
    default:
      return <>You moved in mysterious ways.</>;
  }
};

export default ActivityDetail;
