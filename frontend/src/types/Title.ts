export interface Title {
  key?: string;
  name: string;
  copies: Copy[];
}

interface Copy {
  platform: string;
  service: string;
}

export interface TitleProps {
  title: Title;
}
