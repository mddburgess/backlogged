import { Title } from "./Title";

export interface Activity {
  type: string;
  date: string;
  title: Title;
}

export interface ActivityProps {
  activity: Activity;
}
