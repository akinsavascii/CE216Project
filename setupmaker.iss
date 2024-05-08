[Setup]
AppName=CE216Project
AppVersion=1.0
DefaultDirName={pf}\CE216Project
DefaultGroupName=CE216Project
UninstallDisplayIcon={app}\CE216Project.exe
OutputDir=Setup
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes

[Files]
Source: "build\launch4j\CE216Project.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "build\launch4j\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\CE216Project"; Filename: "{app}\CE216Project.exe"
Name: "{commondesktop}\CE216Project"; Filename: "{app}\CE216Project.exe"; Tasks: desktopicon

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"

[Run]
Filename: "{app}\CE216Project.exe"; Description: "{cm:LaunchProgram,CE216Project}"; Flags: nowait postinstall skipifsilent runascurrentuser 