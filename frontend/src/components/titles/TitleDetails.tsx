import { useEffect } from "react";
import { connect, ConnectedProps } from "react-redux";

import { StoreState } from "../../store";
import { actions } from "../../store/titles";

import CopyList from "./CopyList";

const mapStateToProps = (state: StoreState) => ({
  title: state.titles.details,
});
const mapDispatchToProps = {
  retrieveTitle: actions.retrieve,
  clearDetails: actions.clearDetails,
};
const connector = connect(mapStateToProps, mapDispatchToProps);

type Props = ConnectedProps<typeof connector> & {
  titleKey: string;
};

const TitleDetails = ({ titleKey, title, retrieveTitle, clearDetails }: Props) => {
  useEffect(() => {
    void retrieveTitle(titleKey);
    return () => {
      clearDetails();
    };
  }, [titleKey, retrieveTitle, clearDetails]);

  return title ? (
    <>
      <h2>{title.name}</h2>
      <h6>Copies</h6>
      <CopyList title={title} />
    </>
  ) : (
    <></>
  );
};

export default connector(TitleDetails);
