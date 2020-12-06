export interface Title {
    name: string;
    token: string;
    copies: Copy[];
}

interface Copy {
    platform: string;
    service: string;
}
