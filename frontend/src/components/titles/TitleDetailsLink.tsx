import { Link } from "react-router-dom";

import { TitleProps } from "../../types/Title";

const TitleDetailsLink = ({ title }: TitleProps) => (
  <Link to={`/title/${title.key}`}>{title.name}</Link>
);

export default TitleDetailsLink;
